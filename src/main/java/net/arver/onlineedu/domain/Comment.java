package net.arver.onlineedu.domain;

import java.io.Serializable;

  /**
   * 评论.
   */
public class Comment implements Serializable {

  /**
   * id.
   */
  private Integer id;

  /**
   * content.
   */
  private String content;

  /**
   * userId.
   */
  private Integer userId;

  /**
   * headImg.
   */
  private String headImg;

  /**
   * name.
   */
  private String name;

  /**
   * point.
   */
  private double point;

  /**
   * up.
   */
  private Integer up;

  /**
   * createTime.
   */
  private java.util.Date createTime;

  /**
   * orderId.
   */
  private Integer orderId;

  /**
   * videoId.
   */
  private Integer videoId;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public double getPoint() {
    return point;
  }

  public void setPoint(double point) {
    this.point = point;
  }


  public Integer getUp() {
    return up;
  }

  public void setUp(Integer up) {
    this.up = up;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }


  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }

}
