package com.example.android.newsfeedapp;

import java.time.Instant;
import java.util.Date;

/*
* An {@link NewsArticle} object contains information related to a single news article.
*/
public class NewsArticle {

    // tag to indicate that date data was used in constructor
    private boolean mDateUsed;
    private static final boolean DATE_USED = true;
    private static final boolean DATE_NOT_USED = false;

    // tag to indicate that author data was used in constructor
    private boolean mAuthorUsed;
    private static final boolean AUTHOR_USED = true;
    private static final boolean AUTHOR_NOT_USED = false;

    // required fields for the project
    //-----------------------------------------------------------------
    private String mSectionName;     // source format - String
    private String mWebTitle;        // source format - String
    private String mWebUrl;          // source format - String
    // required for the project if available
    //-----------------------------------------------------------------
    private String mWebPublicationDate;    // source format - Datetime
    private String mAuthor;                // source format - String


    /**
     * Constructs a new {@link NewsArticle} object.
     * Constructor with maximum data, including author and webPubilcationDate
     *
     * @param sectionName is the section article belong
     * @param webTitle is the title of the article
     * @param webUrl is the website URL to find more details and article itself
     * @param webPublicationDate date of the publication (if available)
     * @param author - author of the publication (if available)
     *
     */

    public NewsArticle(String sectionName, String webTitle, String webUrl,  String webPublicationDate, String author) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebPublicationDate = webPublicationDate;
        mAuthor = author;
        mWebUrl = webUrl;
        mDateUsed = DATE_USED;
        mAuthorUsed = AUTHOR_USED;
    }

    /**
     * Constructs a new {@link NewsArticle}.
     * Constructor with minimum data fields used
     *
     * @param sectionName is the section name of the news article
     * @param webTitle is the title of the news article
     * @param webUrl is the webURL for article
     *
     */
    public NewsArticle(String sectionName, String webTitle, String webUrl) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mDateUsed = DATE_NOT_USED;
        mAuthorUsed = AUTHOR_NOT_USED;
    }

    /**
     * Constructs a new {@link NewsArticle}.
     * Constructor with webPublicationDate present
     *
     * @param sectionName is the section name of the news article
     * @param webTitle is the title of the news article
     * @param webUrl is the webURL for article
     * @param webPublicationDate is optional data - pubished date of the article (datetime format)
     */
    public NewsArticle(String sectionName, String webTitle, String webUrl, String webPublicationDate) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mWebPublicationDate = webPublicationDate;
        mAuthor = mWebTitle;
        mDateUsed = DATE_USED;
        mAuthorUsed = AUTHOR_NOT_USED;
    }

    public String getSectionName() { return mSectionName;}
    public String getWebTitle() {return mWebTitle;}
    public String getUrl() {return mWebUrl;}

    // both methods should check if the article contains the data requested.(date and author of publication)
    public Date getDateTimePublication() {
        return Date.from(Instant.parse(mWebPublicationDate));
    }
    public String getAuthor () {return mAuthor;}

    public Boolean hasDate() {return mDateUsed;}
    public Boolean hasAuthor() {return mAuthorUsed;}

}