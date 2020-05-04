package com.example.myfirstejb;

import javax.ejb.Remote;

@Remote
public interface FirstStatelesEJBRemote {

	void insert(String name);
}
