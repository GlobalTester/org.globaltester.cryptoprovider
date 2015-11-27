package org.globaltester.cryptoprovider;

import java.security.Provider;
import java.security.Security;

import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.util.tracker.ServiceTracker;

/**
 * This class is intended to be used as source for the CryptoProvider in order
 * to allow using the code base as single source solution when porting to
 * Android.
 * 
 * @author amay, mboonk, jkoch
 * 
 */
public class Crypto implements ServiceListener {
	
	private static ServiceTracker<Cryptoprovider, Cryptoprovider> serviceTrackerCrypto = null;
	private static Crypto instance;
	private Cryptoprovider cryptoProviderService = null;
	
	/**
	 * Singleton constructor, ensures that the class can not be instantiated from outside.
	 */
	private Crypto(){	
	};
	
	/**
	 * @return singleton instance
	 */
	public static Crypto getInstance() {
		if (instance == null) {
			instance = new Crypto();
		}
		return instance;
	}
	
	
	
	// this variable is solely used for manual overrides
	private static Provider providerObject = null;
	
	/**
	 * WARNING: Manual override only, improper use may result in undesired
	 * behavior!
	 * 
	 * This method manually overrides the {@link Provider} to be returned by
	 * {@link Crypto#getCryptoProvider()} in the future.
	 * Call method with argument "null" to disable manual override.
	 * 
	 * @param newProvider
	 */
	public static void setCryptoProvider(Provider newProvider) {
		providerObject = newProvider;
	}

	public static Provider getCryptoProvider() {
		if (providerObject != null) {
			return providerObject;
		}
		
		return getInstance().getCryptoProviderFromService();
	}
	
	
	
	private Provider getCryptoProviderFromService() {
		if (serviceTrackerCrypto == null && Activator.getContext() != null){
			serviceTrackerCrypto = new ServiceTracker<Cryptoprovider, Cryptoprovider>(Activator.getContext(), Cryptoprovider.class.getName(), null);
			serviceTrackerCrypto.open();
		}
		
		if (serviceTrackerCrypto != null){
			cryptoProviderService = (Cryptoprovider) serviceTrackerCrypto.getService();
			
		    if (cryptoProviderService != null) {
		    	return cryptoProviderService.getCryptoProviderObject();
			}	
		}
	    
	    // use default system provider as fallback
		Provider[] providers = Security.getProviders();
		if (providers != null && providers.length > 0) {
			return providers[0];
		}
		
		// if everything fails
		return null;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		if (event.getType() == ServiceEvent.REGISTERED){
			this.cryptoProviderService = (Cryptoprovider) Activator.getContext().getService(event.getServiceReference());
		} else if (event.getType() == ServiceEvent.UNREGISTERING){
			this.cryptoProviderService = null;
		}
	}

}
