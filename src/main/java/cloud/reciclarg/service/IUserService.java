package cloud.reciclarg.service;

import cloud.reciclarg.model.User;

public interface IUserService {

        public User buscarUserByName(String username) throws Exception;
        //ublic List<User> VerUsuarios();

        public void SaveUsuario(User user) throws Exception;

        public void borrarUsuario(Long id) throws Exception;

        public User buscarUserById(Long id) throws Exception;
    
}
