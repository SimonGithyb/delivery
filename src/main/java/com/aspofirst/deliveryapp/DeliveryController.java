package com.aspofirst.deliveryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class DeliveryController {

    @Autowired
    DeliveryRepository deliveryRepository;

    @GetMapping("")
    public List<Delivery> getAll() {
        return deliveryRepository.getAllOrder();
    }

    @GetMapping("/{id}")
    public Delivery getById(@PathVariable("id") int id) {
        return deliveryRepository.getOrderById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Delivery> orders) {
        return deliveryRepository.save(orders);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Delivery updatedOrder) {
        Delivery movie = deliveryRepository.getOrderById(id);

        if (movie != null) {
            movie.setStatus(updatedOrder.getStatus());
            movie.setDate(updatedOrder.getDate());
            movie.setFrom(updatedOrder.getFrom());
            movie.setZipCodeFrom(updatedOrder.getZipCodeFrom());
            movie.setTo(updatedOrder.getTo());
            movie.setZipCodeTo(updatedOrder.getZipCodeTo());

            deliveryRepository.update(movie);

            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}/status")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Delivery updatedOrder) {
        Delivery order = deliveryRepository.getOrderById(id);

        if (order != null) {
            order.setStatus(updatedOrder.getStatus());

            deliveryRepository.update(order);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return deliveryRepository.delete(id);
    }
}
