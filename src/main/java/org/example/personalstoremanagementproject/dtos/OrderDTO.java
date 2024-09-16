package org.example.personalstoremanagementproject.dtos;

import jakarta.persistence.*;
import org.example.personalstoremanagementproject.entities.Items;
import org.example.personalstoremanagementproject.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    LocalDateTime orderDate;
    Double totalPrice;
    Integer itemQuantity;
    User user;
    List<Items> items;
}
