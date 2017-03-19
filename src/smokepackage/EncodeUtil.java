package smokepackage;
import java.util.Random;

public class EncodeUtil {

	public static String deEncryption(String sSource) {
		if (sSource == null) {
			return null;
		}
		if (sSource.length() < 2) {
			return sSource;
		}
		int nKeyLength = 0;
		int nKeyPos = -1;
		int nOffset = 0;
		StringBuffer sbDest = new StringBuffer();
		int nSrcPos = 0;
		int nSrcAsc;
		int nTmpSrcAsc;
		int nRange = 256;
		String sKey = "Xu Gui";
		nKeyLength = sKey.length();
		nOffset = Integer.parseInt(sSource.substring(0, 2), 16);
		nSrcPos = 2;
		do {
			nSrcAsc = Integer.parseInt(sSource.substring(nSrcPos, nSrcPos + 2),
					16);
			if (nKeyPos < sKey.length() - 1) {
				nKeyPos++;
			} else {
				nKeyPos = 0;
			}
			nTmpSrcAsc = nSrcAsc ^ sKey.charAt(nKeyPos);
			if (nTmpSrcAsc <= nOffset) {
				nTmpSrcAsc = 255 + nTmpSrcAsc - nOffset;
			} else {
				nTmpSrcAsc = nTmpSrcAsc - nOffset;
			}
			sbDest.append(new Character((char) nTmpSrcAsc).charValue());
			nOffset = nSrcAsc;
			nSrcPos += 2;
		} while (nSrcPos < sSource.length());
		return new String(sbDest);
	}
}
