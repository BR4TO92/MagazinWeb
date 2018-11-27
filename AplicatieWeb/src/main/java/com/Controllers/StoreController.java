package com.Controllers;

import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.Beans.MenuBean;

@ManagedBean(name = "storeController", eager = true)
public class StoreController {
	
	public StoreController() {
		System.out.println("Application started!");
	}
	
	public int getGramaj() {
		Integer gramaj = 0;
		
		try  {  
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);                
            SqlSession session = sqlSessionFactory.openSession();
            List<MenuBean> list = session.selectList("getCeva");  
            
            for (MenuBean a : list) {
            	gramaj = a.getGramaj();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gramaj;
	}
	
	public void buttonAction() {
        addMessage("Bun venit in aplicatie!!");
    }
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
