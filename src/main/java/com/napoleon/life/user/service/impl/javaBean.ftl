package ${template.packageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
 import com.napoleon.life.common.persistence.Entity;
 
 public class ${template.className} implements Entity<Long>{  
   
 <#list template.entrys as field> 
 	/**
	 *  ${field.remark}
	 */
    private ${field.type} ${field.value};  
    
 </#list>
 
 <#list template.entrys as field> 
    public ${field.type} get${field.value?cap_first}() {  
        return ${field.value};  
    }  
      
    public void set${field.value?cap_first}(${field.type} ${field.value}) {  
        this.${field.value} = ${field.value};  
    }  
 </#list>
 }  