package net.arver.onlineedu.domain;

import java.io.Serializable;

  /**
   * Chapter.
   */
public class Chapter implements Serializable {

  /**
   * id.
   */
  private Integer id;

  /**
   * videoId.
   */
  private Integer videoId;

  /**
   * title.
   */
  private String title;

  /**
   * ordered.
   */
  private Integer ordered;

  /**
   * createTime.
   */
  private java.util.Date createTime;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public Integer getOrdered() {
    return ordered;
  }

  public void setOrdered(Integer ordered) {
    this.ordered = ordered;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
