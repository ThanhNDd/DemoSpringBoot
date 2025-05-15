package info.thanhnd.demo.mapper;

import info.thanhnd.demo.dto.response.ProductResponse;
import info.thanhnd.demo.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}
