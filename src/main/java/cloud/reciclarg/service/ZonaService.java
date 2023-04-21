package cloud.reciclarg.service;

import cloud.reciclarg.model.Zona;
import cloud.reciclarg.repository.ZonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaService implements IZonaService {

    @Autowired
    public ZonaRepository zonaRepo;

    @Override
    public Zona buscarUserByName(String nombre) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return zonaRepo.findByNombre(nombre);
    }

    @Override
    public List<Zona> VerZonas() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return zonaRepo.findAll();
    }

    @Override
    public void SaveZona(Zona zona) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        zonaRepo.save(zona);
    }

    @Override
    public void borrarZona(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        zonaRepo.deleteById(id);
    }

    @Override
    public Zona buscarZonaById(Long id) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return zonaRepo.findById(id).orElse(null);
    }

}
