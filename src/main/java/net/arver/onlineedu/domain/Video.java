package net.arver.onlineedu.domain;

import java.io.Serializable;

  /**
   * 视频.
   */
public class Video implements Serializable {

  /**
   * id.
   */
  private Integer id;

  /**
   * title.
   */
  private String title;

  /**
   * summary.
   */
  private String summary;

  /**
   * coverImg.
   */
  private String coverImg;

  /**
   * viewNum.
   */
  private Integer viewNum;

  /**
   * price.
   */
  private Integer price;

  /**
   * createTime.
   */
  private java.util.Date createTime;

  /**
   * online.
   */
  private Integer online;

  /**
   * point.
   */
  private double point;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }


  public Integer getViewNum() {
    return viewNum;
  }

  public void setViewNum(Integer viewNum) {
    this.viewNum = viewNum;
  }


  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public Integer getOnline() {
    return online;
  }

  public void setOnline(Integer online) {
    this.online = online;
  }


  public double getPoint() {
    return point;
  }

  public void setPoint(double point) {
    this.point = point;
  }

}
