package com.campususedtrading.mapper;

import com.campususedtrading.pojo.Product;
import com.campususedtrading.pojo.ProductImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("insert into products(name,description,price,category_id,owner_id,created_time,delivery_method,state) " +
            "values(#{name}, #{description}, #{price}, #{categoryId}, #{ownerId}, NOW(), #{deliveryMethod},#{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addProduct(Product product);

    @Insert("<script>" +
            "INSERT INTO product_images(product_id, url, created_time) VALUES " +
            "<foreach collection='list' item='image' index='index' separator=','>" +
            "(#{image.productId}, #{image.url}, NOW())" +
            "</foreach>" +
            "</script>")
    void addProductImages(@Param("list") List<ProductImage> productImages);

    @Select("select * from products where id = #{id}")
    Product getProduct(Integer id);

    @Select("SELECT * FROM product_images WHERE product_id = #{productId}")
    List<ProductImage> getProductImages(Integer productId);

    List<Product> list(Integer categoryId, String state, Integer judge, Integer ownerId);

    @Delete("DELETE FROM products where (id = #{id} AND owner_id = #{ownerId})")
    void deleteProduct(Integer id,Integer ownerId);

    @Update("update products set state = #{state} where id = #{productId}")
    void updateProduct(Integer productId, String state);
}
