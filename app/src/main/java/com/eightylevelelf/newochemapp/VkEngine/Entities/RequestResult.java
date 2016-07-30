package com.eightylevelelf.newochemapp.VkEngine.Entities;

/**
 * Created by Rustam on 30.07.2016.
 */
public class RequestResult<T> {
    private RequestError mError;
    private T mResult;

    public RequestResult(RequestError error)
    {
        setError(error);
    }

    public RequestResult(T result)
    {
        setResult(result);
    }

    public boolean isThereError()
    {
        return mError != null;
    }

    public RequestError getError() {
        return mError;
    }

    public void setError(RequestError error) {
        mError = error;
    }

    public T getResult() {
        return mResult;
    }

    public void setResult(T result) {
        mResult = result;
    }
}
