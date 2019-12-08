package sample.klad;

public class Textile {
    private int id;
    private String type;
    private  String country;
    private  String color;
    private  int longer;
    private  int width;

    public Textile(int id, String type, String country, String color, int longer, int width) {
        this.id = id;
        this.type = type;
        this.country = country;
        this.color = color;
        this.longer = longer;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLonger() {
        return longer;
    }

    public void setLonger(int longer) {
        this.longer = longer;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
