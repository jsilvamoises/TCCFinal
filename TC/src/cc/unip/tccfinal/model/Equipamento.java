/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Moisés
 */
@Entity
@Table(name="tbl_equipamento")
public class Equipamento implements Serializable{
    @EmbeddedId
    private EquipamentoId id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAmostra;

    public Equipamento() {
        id = new EquipamentoId();
    }

    public EquipamentoId getId() {
        return id;
    }

    public Equipamento setId(EquipamentoId id) {
        this.id = id;
        return this;
    }

    public Date getDataAmostra() {
        return dataAmostra;
    }

    public Equipamento setDataAmostra(Date dataAmostra) {
        this.dataAmostra = dataAmostra;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipamento other = (Equipamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
