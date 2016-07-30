package com.eightylevelelf.newochemapp.VkEngine.Entities;

/**
 * Created by Rustam on 31.07.2016.
 */
public class ResultHolder<T>
{
    private T mResult;

    public T getResult() {
        return mResult;
    }

    public void setResult(T result) {
        mResult = result;
    }
}
