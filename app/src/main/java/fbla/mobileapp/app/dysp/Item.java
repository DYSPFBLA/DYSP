package fbla.mobileapp.app.dysp;

/**
 * Created by net.assistant on 1/4/2017.
 */

public class Item {
    private String title;
    private String description;
    private String rating;
    private String price;
    private String category;
    private String createdBy;
    private String ownedBy;
    private Boolean Bought;
    private String additionalComments;
    private long ObjectNumber;
    private String location;
    private String pic;
    //social_media_share for later

    public Item(){

    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getRating(){
        return rating;
    }
    public String getPrice(){
        return price;
    }
    public String getCategory(){
        return  category;
    }
    public String getCreatedBy(){
        return createdBy;
    }
    public String getOwnedBy(){
        return ownedBy;
    }
    public Boolean getBought(){
        return Bought;
    }
    public String getAdditionalComments(){
        return additionalComments;
    }
    public long getObjectNumber(){
        return ObjectNumber;
    }
    public String getLocation(){
        return location;
    }
    public String getPic(){
        return pic;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setRating(String rating){
        this.rating = rating;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    public void setOwnedBy(String ownedBy){
        this.ownedBy = ownedBy;
    }
    public void setBought(Boolean bought){
        this.Bought = bought;
    }
    public void setAdditionalComments (String additionalComments){
        this.additionalComments = additionalComments;
    }
    public void setObjectNumber(Long objectNumber){
        this.ObjectNumber = objectNumber;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setPic(String pic){
        this.pic = pic;
    }
}
