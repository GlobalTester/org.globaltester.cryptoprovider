package org.globaltester.cryptoprovider;

import java.security.Provider;

/**
 * The interface is used to define the Cryptoprovider object. It is implemented
 * by org.globaltester.cryptoprovider.bc (Bouncycastle) and
 * org.globaltester.cryptoprovider.sc (Spongycastle).
 * 
 * @author jkoch
 *
 */
public interface Cryptoprovider {
	
	/**
	 * Returns the Cryptoprovider identifier
	 * @return String: Identifier (e. g. BC for Bouncycastle)
	 */
	public String getCryptoProviderString();
	
	/**
	 * Returns the Cryptoprovider object
	 * @return Provider: The Cryptoprovider
	 */
	public Provider getCryptoProviderObject();
}
