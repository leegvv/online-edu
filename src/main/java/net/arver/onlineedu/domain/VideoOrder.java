package net.arver.onlineedu.domain;

import java.io.Serializable;

  /**
   * VideoOrder.
   */
public class VideoOrder implements Serializable {

  /**
   * id.
   */
  private Integer id;

  /**
   * openid.
   */
  private String openid;

  /**
   * outTradeNo.
   */
  private String outTradeNo;

  /**
   * state.
   */
  private Integer state;

  /**
   * createTime.
   */
  private java.util.Date createTime;

  /**
   * notifyTime.
   */
  private java.util.Date notifyTime;

  /**
   * totalFee.
   */
  private Integer totalFee;

  /**
   * nickname.
   */
  private String nickname;

  /**
   * headImg.
   */
  private String headImg;

  /**
   * videoId.
   */
  private Integer videoId;

  /**
   * videoTitle.
   */
  private String videoTitle;

  /**
   * videoImg.
   */
  private String videoImg;

  /**
   * userId.
   */
  private Integer userId;

  /**
   * ip.
   */
  private String ip;

  /**
   * del.
   */
  private Integer del;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }


  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public java.util.Date getNotifyTime() {
    return notifyTime;
  }

  public void setNotifyTime(java.util.Date notifyTime) {
    this.notifyTime = notifyTime;
  }


  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }


  public String getVideoTitle() {
    return videoTitle;
  }

  public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
  }


  public String getVideoImg() {
    return videoImg;
  }

  public void setVideoImg(String videoImg) {
    this.videoImg = videoImg;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public Integer getDel() {
    return del;
  }

  public void setDel(Integer del) {
    this.del = del;
  }

}
