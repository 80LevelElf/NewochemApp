package com.eightylevelelf.newochemapp.VkEngine.Entities;

import com.vk.sdk.api.VKError;

/**
 * Created by Rustam on 30.07.2016.
 */
public class RequestError {
    private String mErrorMessage;
    private String mInnerMessage;

    public RequestError(String errorMessage, Exception exception)
    {
        this(errorMessage);
        setInnerMessage(exception.getMessage());
    }

    public RequestError(String errorMessage, VKError error)
    {
        this(errorMessage);
        setInnerMessage(error.errorMessage);
    }

    public RequestError(String errorMessage)
    {
        setErrorMessage(errorMessage);
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }

    public String getInnerMessage() {
        return mInnerMessage;
    }

    public void setInnerMessage(String innerMessage) {
        mInnerMessage = innerMessage;
    }
}
