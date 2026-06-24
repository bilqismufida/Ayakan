/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Notif {

    private Tugas tugas;

    public Notif(Tugas tugas) {
        this.tugas = tugas;
    }

    public Tugas getTugas() {
        return tugas;
    }

    public long getSisaHari() {
        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                tugas.getDeadline());
    }

    public boolean perluDitampilkan() {

        long sisaHari = getSisaHari();

        if (sisaHari < 0) {
            return false;
        }

        int batas;

        switch (tugas.getPriority().toLowerCase()) {

            case "high":
                batas = 7;
                break;

            case "medium":
                batas = 5;
                break;

            default:
                batas = 3;
                break;
        }

        return sisaHari <= batas;
    }
}