/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author mmohl
 */
public interface CrudInterface<T> {
    
    public void  Create(T object) throws SQLException;
    public List Read() throws SQLException;
    public void Update(T object) throws SQLException;
    public void Delete(String id) throws SQLException;
    public List Search(String name) throws SQLException;
    
    
}
