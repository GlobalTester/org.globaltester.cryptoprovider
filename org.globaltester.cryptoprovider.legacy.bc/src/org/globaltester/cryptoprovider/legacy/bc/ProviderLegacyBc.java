package org.globaltester.cryptoprovider.legacy.bc;

import java.security.Provider;
import java.security.Security;
import java.util.Hashtable;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.globaltester.cryptoprovider.Cryptoprovider;

public class ProviderLegacyBc implements Cryptoprovider {
	
	private BouncyCastleProvider provider;
	
	public ProviderLegacyBc() {
		 provider = new BouncyCastleProvider();
		 System.out.println("CryptoProvider is " + provider);
		 Security.addProvider(provider);
	}
	
	@Override
	public String getCryptoProviderString() {
		return provider.getInfo(); // returns "BC"
	}
	
	@Override
	public Provider getCryptoProviderObject() {
		return provider;
	}
	
	@Override
	public Hashtable<String, String> getProperties() {
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(Cryptoprovider.NAME, getCryptoProviderObject().getName());
		props.put(Cryptoprovider.VERSION, String.valueOf(getCryptoProviderObject().getVersion()));
		props.put(Cryptoprovider.INFO, getCryptoProviderObject().getInfo());
		return props;
	}
	
}
