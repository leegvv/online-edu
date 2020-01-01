package net.arver.onlineedu.domain;

import java.io.Serializable;

  /**
   * Episode.
   */
public class Episode implements Serializable {

  /**
   * id.
   */
  private Integer id;

  /**
   * title.
   */
  private String title;

  /**
   * num.
   */
  private Integer num;

  /**
   * duration.
   */
  private String duration;

  /**
   * coverImg.
   */
  private String coverImg;

  /**
   * videoId.
   */
  private Integer videoId;

  /**
   * summary.
   */
  private String summary;

  /**
   * createTime.
   */
  private java.util.Date createTime;

  /**
   * chapterId.
   */
  private Integer chapterId;



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


  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }


  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }


  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }


  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public Integer getChapterId() {
    return chapterId;
  }

  public void setChapterId(Integer chapterId) {
    this.chapterId = chapterId;
  }

}
