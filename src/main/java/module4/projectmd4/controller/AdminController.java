package module4.projectmd4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import module4.projectmd4.constant.EHttpStatus;
import module4.projectmd4.model.dto.request.FormCategory;
import module4.projectmd4.model.dto.request.FormProduct;
import module4.projectmd4.model.dto.response.ResponseWrapper;
import module4.projectmd4.model.entity.Category;
import module4.projectmd4.model.entity.Product;
import module4.projectmd4.model.entity.User;
import module4.projectmd4.service.ICategoryService;
import module4.projectmd4.service.IProductService;
import module4.projectmd4.service.IRoleService;
import module4.projectmd4.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IUserService userService;
    private final IRoleService roleService;
    private final ICategoryService categoryService;
    private final IProductService productService;

    //    Roles
    @GetMapping("/role")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    //    User management
    @GetMapping("/user/list/{page}")
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(defaultValue = "") String keyword,
            @PathVariable int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<User> users = userService.findUsersByKeyword(keyword, page - 1, size, sortBy, sortDirection);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user/block/{id}")
    public ResponseEntity<?> blockUser(@PathVariable long id) {
        String action = userService.block(id);
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .eHttpStatus(EHttpStatus.SUCCESS)
                        .statusCode(HttpStatus.CREATED.value())
                        .data("User has been " + action + " successfully!")
                        .build(),
                HttpStatus.CREATED);
    }

    //    Category management
    @GetMapping("/category/list/{page}")
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(defaultValue = "") String keyword,
            @PathVariable int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<Category> categories = categoryService.findCategoriesByKeyword(keyword, page - 1, size, sortBy, sortDirection);
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/category/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody FormCategory formCategory) {
        categoryService.add(formCategory);
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .eHttpStatus(EHttpStatus.SUCCESS)
                        .statusCode(HttpStatus.CREATED.value())
                        .data("Category added successfully!")
                        .build(),
                HttpStatus.CREATED);
    }

//    Product management
@GetMapping("/product/list/{page}")
public ResponseEntity<Page<Product>> getProducts(
        @RequestParam(defaultValue = "") String keyword,
        @PathVariable int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDirection) {
    Page<Product> categories = productService.findProductsByKeyword(keyword, page - 1, size, sortBy, sortDirection);
    return ResponseEntity.ok(categories);
}

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody FormProduct formProduct) {
        productService.add(formProduct);
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .eHttpStatus(EHttpStatus.SUCCESS)
                        .statusCode(HttpStatus.CREATED.value())
                        .data("Product added successfully!")
                        .build(),
                HttpStatus.CREATED);
    }

}
