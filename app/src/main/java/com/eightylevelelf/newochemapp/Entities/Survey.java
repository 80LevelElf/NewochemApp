package com.eightylevelelf.newochemapp.Entities;

import java.util.Date;
import java.util.List;

/**
 * Created by Rustam on 30.07.2016.
 */
public class Survey {

    public static class Answer
    {
        private String mDescription;
        private double mRate;

        public Answer(String description, double rate)
        {
            setDescription(description);
            setRate(rate);
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public double getRate() {
            return mRate;
        }

        public void setRate(double rate) {
            mRate = rate;
        }
    }

    private String mDescription;
    private Date mDate;
    private List<Answer> mAnswerList;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<Answer> getAnswerList() {
        return mAnswerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        mAnswerList = answerList;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
