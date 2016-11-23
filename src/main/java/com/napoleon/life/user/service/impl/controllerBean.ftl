package ${template.controllerPackageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
@Controller
@RequestMapping("/${template.requestPre}/${template.requestSuffix}")
public class ${template.controllerName} extends BaseController{

	@Autowired
	private ${template.facadeName} ${template.facadeNameLowerCase};
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit${template.javaBeanName}(@ParamValid ${template.editDtoBeanName} editDto) {
		return this.${template.facadeNameLowerCase}.edit${template.javaBeanName}(editDto);
    }
    
    @ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete${template.javaBeanName}(@ParamValid LifeDeleteDto deleteDto) {
		return this.${template.facadeNameLowerCase}.delete${template.javaBeanName}(deleteDto);
    }
}
 
