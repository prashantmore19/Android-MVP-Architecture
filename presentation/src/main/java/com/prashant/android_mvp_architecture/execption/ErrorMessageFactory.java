package com.prashant.android_mvp_architecture.execption;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.prashant.android_mvp_architecture.R;
import com.prashant.domain.exception.InternalServerException;
import com.prashant.domain.exception.NetworkConnectionException;
import com.prashant.domain.exception.NotFoundException;
import com.prashant.domain.exception.ServiceUnavailableException;
import com.prashant.domain.exception.UnauthorizedException;
import com.prashant.domain.exception.UnknownException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import timber.log.Timber;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context   Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Exception exception) {
        Timber.e("called create !");

        if (context == null) {
            Timber.e(exception.getMessage());
            return "There was an application error";
        }

        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_connection_failure);
        } else if (exception instanceof UnauthorizedException) {
            message = context.getString(R.string.exception_message_unauthorized_user);
        } else if (exception instanceof ServiceUnavailableException) {
            message = context.getString(R.string.exception_message_service_unavailable);
        } else if (exception instanceof InternalServerException) {
            message = context.getString(R.string.exception_message_server_error);
        } else if (exception instanceof NotFoundException) {
            message = context.getString(R.string.exception_message_not_found);
        } else if (exception instanceof UnknownHostException) {
            message = context.getString(R.string.exception_message_connection_failure);
        } else if (exception instanceof ConnectException) {
            message = context.getString(R.string.exception_message_connection_failure);
        } else if (exception instanceof SocketTimeoutException) {
            message = context.getString(R.string.exception_message_connection_failure);
        } else if (exception instanceof UnknownException) {
            message = exception.getMessage();
        }

        Timber.e("error message====>" + message);
        Timber.e("ErrorMessageFactory create ==>> " + new GsonBuilder().setPrettyPrinting().create().toJson(exception));
        return message;
    }

}