package org.globaltester.cryptoprovider.bc.legacy_1_46;

import java.security.spec.ECParameterSpec;

import org.bouncycastle.jce.provider.asymmetric.ec.EC5Util;

public class CryptoConverterUtil {

	public static org.bouncycastle.jce.spec.ECParameterSpec convertSpec(ECParameterSpec ecSpec) {
		return EC5Util.convertSpec(ecSpec, true);
	}
}
