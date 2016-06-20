
/* First created by JCasGen Fri Apr 15 11:50:23 CEST 2016 */
package fr.lirmm.advanse.chv.definitionMining.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Jun 15 11:44:47 CEST 2016
 * @generated */
public class ChunkTreetagger_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ChunkTreetagger_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ChunkTreetagger_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ChunkTreetagger(addr, ChunkTreetagger_Type.this);
  			   ChunkTreetagger_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ChunkTreetagger(addr, ChunkTreetagger_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ChunkTreetagger.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
 
  /** @generated */
  final Feature casFeat_tagType;
  /** @generated */
  final int     casFeatCode_tagType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTagType(int addr) {
        if (featOkTst && casFeat_tagType == null)
      jcas.throwFeatMissing("tagType", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tagType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTagType(int addr, String v) {
        if (featOkTst && casFeat_tagType == null)
      jcas.throwFeatMissing("tagType", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    ll_cas.ll_setStringValue(addr, casFeatCode_tagType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ChunkTreetagger_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tagType = jcas.getRequiredFeatureDE(casType, "tagType", "uima.cas.String", featOkTst);
    casFeatCode_tagType  = (null == casFeat_tagType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tagType).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

  }
}



    