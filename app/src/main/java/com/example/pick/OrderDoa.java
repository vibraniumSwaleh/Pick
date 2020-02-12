package com.example.pick;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDoa {

    @Insert
    void insert(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("DELETE FROM order_table")
    void deleteAllOrders();

    @Query("SELECT * FROM order_table ORDER BY id ASC")
    LiveData<List<Order>> getAllOrders();
}
