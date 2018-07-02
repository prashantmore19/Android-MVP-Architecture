package com.prashant.data.util;

import com.prashant.data.constant.DataConstant;
import com.prashant.domain.exception.BadRequestException;
import com.prashant.domain.exception.ForbiddenException;
import com.prashant.domain.exception.InternalServerException;
import com.prashant.domain.exception.NotFoundException;
import com.prashant.domain.exception.ServiceUnavailableException;
import com.prashant.domain.exception.UnauthorizedException;
import com.prashant.domain.exception.UnknownException;

import java.io.IOException;

import okhttp3.RequestBody;
import okio.Buffer;

public class DataUtility {

    public static DataUtility INSTANCE = null;
    public static final String NEWLINE = "\n";
    public static final String ERROR_CODE = "Error code ==>> ";
    public static final String ERROR_MESSAGE = "Message ==>> ";

    public static DataUtility instance() {
        if (INSTANCE == null) {
            INSTANCE = new DataUtility();
        }
        return INSTANCE;
    }

    public String generateRestAPIError(int responseCode, String responseMessage) {
        return NEWLINE + ERROR_CODE + responseCode + NEWLINE + ERROR_MESSAGE + (responseMessage != null ? responseMessage : "");
    }

    public Exception generateRestAPIException(int responseCode, String errorMessage) {
        if (responseCode == DataConstant.EXCEPTION_BAD_REQUEST_STATUS_CODE) {
            return new BadRequestException(errorMessage);
        } else if (responseCode == DataConstant.EXCEPTION_UNAUTHORIZED_STATUS_CODE) {
            return new UnauthorizedException(errorMessage);
        } else if (responseCode == DataConstant.EXCEPTION_FORBIDDEN_STATUS_CODE) {
            return new ForbiddenException(errorMessage);
        } else if (responseCode == DataConstant.EXCEPTION_NOT_FOUND_STATUS_CODE) {
            return new NotFoundException(errorMessage);
        } else if (responseCode == DataConstant.EXCEPTION_INTERNAL_SERVER_STATUS_CODE) {
            return new InternalServerException(errorMessage);
        } else if (responseCode == DataConstant.EXCEPTION_SERVICE_UNAVAILABLE_STATUS_CODE) {
            return new ServiceUnavailableException(errorMessage);
        } else {
            return new UnknownException(errorMessage);
        }
    }

    public static String requestBodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        } catch (Exception | OutOfMemoryError e) {
            return e.getMessage();
        }
    }

}