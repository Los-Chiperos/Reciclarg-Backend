package cloud.reciclarg.service;

import cloud.reciclarg.model.User;

//Esta interfaz define un conjunto de métodos que deben ser implementados por cualquier clase que la implemente.
public interface IUserService {

    //Este método busca un usuario por su nombre de usuario y devuelve un objeto User. Puede lanzar una excepción de tipo Exception.
    User buscarUserByName(String username) throws Exception;

    //Este método se utiliza para guardar un objeto User en alguna fuente de datos. Puede lanzar una excepción de tipo Exception.
    void SaveUsuario(User user) throws Exception;

    //Este método se encarga de eliminar un usuario según su ID. Puede lanzar una excepción de tipo Exception.
    void borrarUsuario(Long id) throws Exception;

    //Este método busca un usuario por su ID y devuelve un objeto User. Puede lanzar una excepción de tipo Exception.
    User buscarUserById(Long id) throws Exception;

}
