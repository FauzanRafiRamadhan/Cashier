package com.smk.cashier;

import Dao.BarangDao;
import com.smk.cashier.model.Barang;
import com.smk.cashier.service.BarangService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BarangServiceTest {

    private Barang laptop;

    @Test
    @Order(2)
    void getBarangList() {
        List<Barang> barangList = BarangService.getInstance().getBarangList();
        assertEquals(barangList.size(),3);
    }

    @Test
    @Order(3)
    void findByName() {
        List<Barang> resultList = BarangService.getInstance().findByName("Laptop Gaming");
        assertEquals(resultList.size(),2);
    }

    @Test
    @Order(1)
    void addBarang() {
        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(5000000);
        BarangService.getInstance().addBarang(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("M0001");
        mouse.setNamaBarang("Mouse");
        mouse.setHargaBarang(100000);
        BarangService.getInstance().addBarang(mouse);

        Barang laptopGaming = new Barang();
        laptop.setKodeBarang("LP002");
        laptop.setNamaBarang("Laptop Gaming");
        laptop.setHargaBarang(20000000);
        BarangService.getInstance().addBarang(laptopGaming);

    }

    @Test
    @Order(4)
    void saveBarangToDatabase() {
        BarangDao barangDao = new BarangDao();
        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(5000000);
        laptop.setDateCreated(new Date());
        laptop.setLastModified(new Date());
        barangDao.save(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("MO001");
        mouse.setNamaBarang("Mouse");
        mouse.setHargaBarang(500000);
        mouse.setDateCreated(new Date());
        mouse.setLastModified(new Date());
        barangDao.save(mouse);

        Barang laptopGaming = new Barang();
        laptopGaming.setKodeBarang("LP0002");
        laptopGaming.setNamaBarang("Laptop" + "Gaming");
        laptopGaming.setHargaBarang(20000000);
        laptopGaming.setDateCreated(new Date());
        laptopGaming.setLastModified(new Date());
        barangDao.save(laptopGaming);

    }
    @Test
    @Order(5)
    void getDataById() {
        BarangDao barangDao = new BarangDao();
        Optional<Barang> barang1 = barangDao.get(4);
        barang1.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Laptop", barang.getNamaBarang());
                assertEquals("LP001", barang.getKodeBarang());
            }
        });

        Optional<Barang> barang2 = barangDao.get(5);
        barang2.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Mouse", barang.getNamaBarang());
                assertEquals("MO001", barang.getKodeBarang());
            }
        });
    }
    @Test
    @Order(6)
    void updateBarangByKodeBarang(){
        BarangDao  barangDao = new BarangDao();
        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop Updated");
        laptop.setHargaBarang(6000000);
        barangDao.update(laptop);
        Optional<Barang> barang1 = barangDao.get(4);
        barang1.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Laptop Updated", barang.getNamaBarang());
                assertEquals("LP001", barang.getKodeBarang());
                assertEquals(6000000, barang.getHargaBarang());
            }
        });

    }

    @Test
    @Order(7)
    void deleteBarang(){
        BarangDao barangDao = new BarangDao();
        laptop.setKodeBarang("LP001");
        barangDao.delete(laptop);

        Optional<Barang> barang1 = barangDao.get(1);
         assertEquals(barang1.isPresent(),false);
    }

    @Test
    @Order(8)
    void searchBarang(){
        BarangDao barangDao = new BarangDao();

        Barang bluetoothKeyboard = new Barang();

        bluetoothKeyboard.setKodeBarang("BL001");
        bluetoothKeyboard.setNamaBarang("Bluetooth Keyboard");
        bluetoothKeyboard.setHargaBarang(500000);
        bluetoothKeyboard.setDateCreated(new Date());
        bluetoothKeyboard.setLastModified(new Date());

        barangDao.save(bluetoothKeyboard);

        Barang bluetoothMouse = new Barang();

        bluetoothMouse.setKodeBarang("");
        bluetoothMouse.setNamaBarang("");
        bluetoothMouse.setHargaBarang(300000);
        bluetoothMouse.setDateCreated(new Date());
        bluetoothMouse.setLastModified(new Date());

        barangDao.save(bluetoothMouse);

        Barang mechanicalKeyboard = new Barang();

        mechanicalKeyboard.setKodeBarang("KB001");
        mechanicalKeyboard.setNamaBarang("Mechanical Keyboard");
        mechanicalKeyboard.setHargaBarang(2000000);
        mechanicalKeyboard.setDateCreated(new Date());
        mechanicalKeyboard.setLastModified(new Date());


        barangDao.save(mechanicalKeyboard);

        assertEquals(barangDao.search("Mecha") .size(), 1);
        assertEquals(barangDao.search("Key") .size(),2);
        assertEquals(barangDao.search("BL").size(),3);
    }
}
