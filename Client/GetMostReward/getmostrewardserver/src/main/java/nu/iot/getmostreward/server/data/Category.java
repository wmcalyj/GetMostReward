package nu.iot.getmostreward.server.data;


import nu.iot.getmostreward.server.data.CategoryEnum;

/**
 * Created by mengchaowang on 5/8/16.
 */
public class Category {

    private CategoryEnum category;
    private String categoryDescription;
    private String categoryExclusion;

    private Category() {
        // Disable default Category
    }

    public Category(CategoryEnum category) {
        this.category = category;
    }

    public Category(String category) {
        this.category = CategoryEnum.valueOf(category.toUpperCase());
    }

    public Category(CategoryEnum category, String categoryDescription, String
            categoryExclusion) {
        this.category = category;
        if (categoryDescription != null && categoryDescription.length() > 0) {
            this.categoryDescription = categoryDescription;
        }
        if (categoryExclusion != null && categoryExclusion.length() > 0) {
            this.categoryExclusion = categoryExclusion;
        }
    }

    public Category(String category, String categoryDescription, String
            categoryExclusion) {
        this.category = CategoryEnum.valueOf(category.toUpperCase());
        if (categoryDescription != null && categoryDescription.length() > 0) {
            this.categoryDescription = categoryDescription;
        }
        if (categoryExclusion != null && categoryExclusion.length() > 0) {
            this.categoryExclusion = categoryExclusion;
        }
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryExclusion() {
        return categoryExclusion;
    }

    public void setCategoryExclusion(String categoryExclusion) {
        this.categoryExclusion = categoryExclusion;
    }


}
