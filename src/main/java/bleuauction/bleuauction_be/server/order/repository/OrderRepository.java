package bleuauction.bleuauction_be.server.order.repository;

import bleuauction.bleuauction_be.server.member.entity.Member;
import bleuauction.bleuauction_be.server.order.entity.Order;
import bleuauction.bleuauction_be.server.store.entity.Store;
import jakarta.persistence.EntityManager;
import java.util.Optional;

import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
  @PersistenceContext
  private EntityManager em;

  public Order save(Order order) {
    // calculate the order price
    order.setOrderPrice(order.calculateOrderPrice());
    em.persist(order);
    return order;
  }
  public Order findOne(Long orderNo) {
    return em.find(Order.class, orderNo);
  }

  public List<Order> findAll() {
    List<Order> result = em.createQuery("select o from Order o", Order.class)
            .getResultList();
    return result;
  }

public List<Order> findByOrderMenusMemberMemberNo(@Param("memberNo") Member memberNo) {
  return em.createQuery("SELECT o\n" +
                  "FROM Order o\n" +
                  "WHERE o.orderNo IN (\n" +
                  "    SELECT om.orderNo\n" +
                  "    FROM OrderMenu om\n" +
                  "    WHERE om.memberNo = :memberNo\n" +
                  ")").setParameter("memberNo", memberNo) // named parameter를 설정
          .getResultList();
}


  public List<Order> findOrdersByMemberAndStore(Long memberNo) {
    return em.createQuery(
                    "SELECT o " +
                            "FROM Order o " +
                            "JOIN o.OrderMenus om " +
                            "JOIN om.menuNo menu " +
                            "JOIN menu.storeNo store " +
                            "JOIN store.memberNo member " +
                            "WHERE o.orderStatus = 'Y' AND member.memberNo = :memberNo", Order.class)
            .setParameter("memberNo", memberNo)
            .getResultList();
  }





  public Optional<Order> findById(Long orderNo) {
    Order order = em.find(Order.class, orderNo);
    return Optional.ofNullable(order);
  }
}
