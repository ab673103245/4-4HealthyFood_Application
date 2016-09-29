package qianfeng.a4_4healthyfood_application;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class ClassfyBean {

    private String id;
    private String name;

    public ClassfyBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassfyBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
