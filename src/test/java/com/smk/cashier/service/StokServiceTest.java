package com.smk.cashier.service;

import Dao.StokDao;
import com.smk.cashier.model.Stok;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StokServiceTest {

    @Test
    @Order(2)
    void getStokList() {
        List<Stok> barangList = StokService.getInstance().getStokList();
        assertEquals(barangList.size(),3);
    }

    @Test
    @Order(3)
    void findByKode() {
        List<Stok> resultList = StokService.getInstance().findByKode("LP002");
        assertEquals(resultList.size(),1);
    }

    @Test
    @Order(1)
    void addStok() {
        Stok laptop = new Stok();
        laptop.setId(1);
        laptop.setKodebarang("LP001");
        laptop.setStokbarang(3);
        StokService.getInstance().addStok(laptop);

        Stok mouse = new Stok();
        mouse.setId(2);
        mouse.setKodebarang("M0001");
        mouse.setStokbarang(4);
        StokService.getInstance().addStok(mouse);

        Stok laptopGaming = new Stok();
        laptop.setId(3);
        laptop.setKodebarang("LP002");
        laptop.setStokbarang(6);
        StokService.getInstance().addStok(laptopGaming);

    }
    @Test
    @Order(2)
    void getById() {
        StokDao dao = new StokDao();
        Optional<Stok> s1 = dao.get(1);
        s1.ifPresent(new Consumer<Stok>() {
            @Override
            public void accept(Stok stock) {
                assertEquals("B001", stock.getKodebarang());
                assertEquals(10, stock.getStokbarang());
            }
        });

        Optional<Stok> s2 = dao.get(2);
        s2.ifPresent(new Consumer<Stok>() {
            @Override
            public void accept(Stok stock) {
                assertEquals("B002", stock.getKodebarang());
                assertEquals(15, stock.getStokbarang());
            }
        });

        Optional<Stok> s3 = dao.get(3);
        s3.ifPresent(new Consumer<Stok>() {
            @Override
            public void accept(Stok stock) {
                assertEquals("B003", stock.getKodebarang());
                assertEquals(5, stock.getStokbarang());
            }
        });
    }
}