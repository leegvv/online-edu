package net.arver.onlineedu.domain;

import java.io.Serializable;

  /**
   * User.
   */
public class User implements Serializable {

  /**
   * id.
   */
  private Integer id;

  /**
   * openid.
   */
  private String openid;

  /**
   * name.
   */
  private String name;

  /**
   * headImg.
   */
  private String headImg;

  /**
   * phone.
   */
  private String phone;

  /**
   * sign.
   */
  private String sign;

  /**
   * sex.
   */
  private Integer sex;

  /**
   * city.
   */
  private String city;

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


  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }


  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
