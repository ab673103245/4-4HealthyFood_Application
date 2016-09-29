package qianfeng.a4_4healthyfood_application;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class FoodBean {
    private String description;
    private String img;
    private String keywords;

    public FoodBean(String description, String img, String keywords) {
        this.description = description;
        this.img = img;
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
