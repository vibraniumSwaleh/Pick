package com.example.pick;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderRepository {

    private OrderDoa orderDoa;
    private LiveData<List<Order>> allOrders;

    public OrderRepository(Application application){
        OrderDatabase database = OrderDatabase.getInstance(application);
        orderDoa = database.orderDoa();
        allOrders = orderDoa.getAllOrders();
    }

    public void insert(Order order){
        new InsertOrderAsyncTask(orderDoa).execute(order);
    }

    public void update(Order order){
        new UpdateOrderAsyncTask(orderDoa).execute(order);
    }

    public void delete(Order order){
        new DeleteOrderAsyncTask(orderDoa).execute(order);
    }

    public void deleteAllOrders(){
        new DeleteAllOrderAsyncTask(orderDoa).execute();

    }

    public LiveData<List<Order>> getAllOrders(){
        return allOrders;
    }

    private static class InsertOrderAsyncTask extends AsyncTask<Order, Void, Void>{
        private OrderDoa orderDoa;

        private InsertOrderAsyncTask(OrderDoa orderDoa){
            this.orderDoa = orderDoa;
        }
        @Override
        protected Void doInBackground(Order... orders) {
            orderDoa.insert(orders[0]);
            return null;
        }
    }

    private static class UpdateOrderAsyncTask extends AsyncTask<Order, Void, Void>{
        private OrderDoa orderDoa;

        private UpdateOrderAsyncTask(OrderDoa orderDoa){
            this.orderDoa = orderDoa;
        }
        @Override
        protected Void doInBackground(Order... orders) {
            orderDoa.update(orders[0]);
            return null;
        }
    }

    private static class DeleteOrderAsyncTask extends AsyncTask<Order, Void, Void>{
        private OrderDoa orderDoa;

        private DeleteOrderAsyncTask(OrderDoa orderDoa){
            this.orderDoa = orderDoa;
        }
        @Override
        protected Void doInBackground(Order... orders) {
            orderDoa.delete(orders[0]);
            return null;
        }
    }

    private static class DeleteAllOrderAsyncTask extends AsyncTask<Void, Void, Void>{
        private OrderDoa orderDoa;

        private DeleteAllOrderAsyncTask(OrderDoa orderDoa){
            this.orderDoa = orderDoa;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            orderDoa.deleteAllOrders();
            return null;
        }
    }

}
