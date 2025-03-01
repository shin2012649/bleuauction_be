package bleuauction.bleuauction_be.server.storeItemDailyPrice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ba_store_item_daily_price")
public class StoreItemDailyPrice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dailyPriceNo;

  private Long storeNo;

  private int dailyPrice;

  private String itemCode;

  private String itemName;

  private String itemSize;

  @Enumerated(EnumType.STRING)
  private OriginStatus originStatus;

  @Enumerated(EnumType.STRING)
  private OriginPlaceStatus originPlaceStatus;

  @Enumerated(EnumType.STRING)
  private WildFarmStatus wildFarmStatus;

  private Date daliyPriceDate;

  @CreationTimestamp
  private Timestamp regDatetime;

  @UpdateTimestamp
  private Timestamp mdfDatetime;

  @Enumerated(EnumType.STRING)
  private DailyPriceStatus dailyPriceStatus;

}
