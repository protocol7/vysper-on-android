package com.protocol7.android.vysper;

import java.io.InputStream;

import org.apache.vysper.mina.C2SEndpoint;
import org.apache.vysper.storage.StorageProviderRegistry;
import org.apache.vysper.storage.inmemory.MemoryStorageProviderRegistry;
import org.apache.vysper.xmpp.addressing.EntityImpl;
import org.apache.vysper.xmpp.authentication.AccountManagement;
import org.apache.vysper.xmpp.server.XMPPServer;

import android.app.Activity;
import android.os.Bundle;

public class VysperOnAndroid extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
			startServer();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    
    private void startServer() throws Exception {
        StorageProviderRegistry providerRegistry = new MemoryStorageProviderRegistry();

        AccountManagement accountManagement = (AccountManagement) providerRegistry.retrieve(AccountManagement.class);
        accountManagement.addUser(EntityImpl.parseUnchecked("test@vysper.org"), "test");
        
        XMPPServer server = new XMPPServer("vysper.org");
        server.addEndpoint(new C2SEndpoint());
        server.setStorageProviderRegistry(providerRegistry);

    	InputStream cert = VysperOnAndroid.class.getClassLoader().getResourceAsStream("keystore.bks");
    	
		server.setTLSCertificateInfo(cert, "boguspw", "BKS");
		
		server.start();
    }
    
}
