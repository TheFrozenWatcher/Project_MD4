package module4.projectmd4.model.entity;

import jakarta.persistence.*;
import lombok.*;
import module4.projectmd4.constant.OrderStatus;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @Column(precision = 10, scale = 2)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date receivedAt;

}
