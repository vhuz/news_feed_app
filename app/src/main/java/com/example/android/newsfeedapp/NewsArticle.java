package com.example.android.newsfeedapp;

/*
* An {@link NewsArticle} object contains information related to a single news article.
*/
public class NewsArticle {

    private String mId;
    private String mType;
    private String mSectionId;
    // required for the project
    private String mSectionName;
    // required for the project if available
    private String mWebPublicationDate;
    // required for the project
    private String mWebTitle;
    // need so we can pass it in intent for webbrowser
    private String mWebUrl;
    private String mApiUrl;
    private String mIsHosted;
    private String mPillarId;
    private String mPillarName;
    // example of webTitle - 	"Coronavirus is our chance to completely rethink what the economy is for | Malcolm Bull"
    // author is listed after separator |
    // required for the project if available
    private String mAuthor;

    /**
     * Constructs a new {@link NewsArticle} object.
     *
     * @param sectionName is the section article belong
     * @param webTitle is the title of the article
     * @param webPublicationDate date of the publication (if available)
     * @param author - author of the publication (if available)
     * @param url is the website URL to find more details and article itself
     */
    public NewsArticle(String sectionName, String webTitle, String webPublicationDate, String author, String url) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebPublicationDate = webPublicationDate;
        mAuthor = author;
        mWebUrl = url;
    }

    public String getSectionName() { return mSectionName;}
    public String getWebTitle() {return mWebTitle;}
    public String getWebUrl() {return mWebUrl;}
    // both methods should check if the article contains the data requested.(date and author of publication)
    public String getmWebPublicationDate() {return mWebPublicationDate;}
    public String getAuthor () {return mAuthor;}

}