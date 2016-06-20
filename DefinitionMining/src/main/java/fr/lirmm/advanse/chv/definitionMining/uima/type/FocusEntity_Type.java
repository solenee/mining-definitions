
/* First created by JCasGen Wed Jun 15 10:55:37 CEST 2016 */
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
public class FocusEntity_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (FocusEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = FocusEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new FocusEntity(addr, FocusEntity_Type.this);
  			   FocusEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new FocusEntity(addr, FocusEntity_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = FocusEntity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");



  /** @generated */
  final Feature casFeat_Text;
  /** @generated */
  final int     casFeatCode_Text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_Text == null)
      jcas.throwFeatMissing("Text", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_Text == null)
      jcas.throwFeatMissing("Text", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_Text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_DefinitionText;
  /** @generated */
  final int     casFeatCode_DefinitionText;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDefinitionText(int addr) {
        if (featOkTst && casFeat_DefinitionText == null)
      jcas.throwFeatMissing("DefinitionText", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_DefinitionText);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDefinitionText(int addr, String v) {
        if (featOkTst && casFeat_DefinitionText == null)
      jcas.throwFeatMissing("DefinitionText", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_DefinitionText, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public FocusEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Text = jcas.getRequiredFeatureDE(casType, "Text", "uima.cas.String", featOkTst);
    casFeatCode_Text  = (null == casFeat_Text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Text).getCode();

 
    casFeat_DefinitionText = jcas.getRequiredFeatureDE(casType, "DefinitionText", "uima.cas.String", featOkTst);
    casFeatCode_DefinitionText  = (null == casFeat_DefinitionText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DefinitionText).getCode();

  }
}



    