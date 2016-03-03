package org.globaltester.cryptoprovider.bc;

import java.security.Provider;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.globaltester.cryptoprovider.Cryptoprovider;

public class ProviderBc implements Cryptoprovider {
	
	private BouncyCastleProvider provider;
	
	public ProviderBc() {
		 provider = new BouncyCastleProvider();
		 System.out.println("CryptoProvider is " + provider);
		 Security.addProvider(provider);
	}

	@Override
	public String getCryptoProviderString() {
		return provider.getName(); // returns "BC"
	}

	@Override
	public Provider getCryptoProviderObject() {
		return provider;
	}

}
