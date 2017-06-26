package com.example.b2c.net.response;

import java.io.Serializable;

/**
 * Created by ThinkPad on 2017/4/19.
 */

public class ShareInfo implements Serializable{
    private String shareDetails;
    private String shareName;
    private String sharePic;

    public String getShareDetails() {
        return shareDetails;
    }

    public void setShareDetails(String shareDetails) {
        this.shareDetails = shareDetails;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    private String shareUrl;
}
