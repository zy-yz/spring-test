**spring实现IOC的思路和方法**

​        spring实现IOC的思路是提供一些配置信息用来描述类之间的依赖关系，然后由容器来解析这些配置信息，继而维护好对象之间的依赖关系，前提是对象之间的依赖关系必须在类中定义好，比如A.class中有一个B.class的属性，那么我们可以理解为A依赖了B。

**Spring实现IOC的具体思路大致为**

​		1.应用程序中提供类，提供依赖关系(属性或者构造方法)

​		2.把需要交给容器管理的对象通过配置信息告诉容器(xml,annnotation,javaconfig)

​		3.把各个类之间的依赖关系通过配置信息告诉容器

**维护的过程称为自动注入**

​		自动注入的方法主要有构造方法和setter

**自动装配**

​		no  //无需自动装配

​		byName  //按名称自动装配bean属性

​		byType  //按类型自动装配bean属性     autowired

​		constructor  //按构造器自动装配

**springbean的作用域**

​		singleton 默认的作用域,在Spring IOC 容器仅存在一个Bean实例，Bean以单例方式存在

(@Scope("singleton"))

​		prototype 每次从容器调用bean时，都会返回一个新的实例，也就是每次调用getBean()时都会实例化一个新的bean

​		request 每次HTTP请求都会创建一个新的Bean，该作用于仅适用于web环境

​		session 每个HTTP Session共享一个Bean，不同的Session使用不同的Bean，同样只适用于web环境

​		Global Session 一般作用于Portlet应用环境，只作用于Web环境。

作用域singleton在应用一个Prototype的时候引发的问题

​		https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-factory-method-injection

​		

**spring生命周期和回调**

​		Spring在容器初始化bean之后（完成依赖注入后）和销毁前都提供了回调的方法，我们称之为生命周期的回调方法。

​		1.接口方式

​		第一种是实现Spring中的InitializingBean 和 DisposableBean接口，实现其对应的afterPropertiesSet（）方法以及destroy()方法。afterPropertiesSet（）方法将会在Spring容器完成属性注入之后进行调用，destroy（）将在Spring销毁bean之前进行调用。这种方式有个缺点就是会让你的代码和Spring耦合

​		2、注解方式

​		通过使用@PostConstruct 和 @PreDestroy 注解也可以完成相同的功能。这两个注解是由JDK提供的，Spring通过CommonAnnotationBeanPostProcessor 这个后置处理器进行解析和处理。

​		3、init-method和destroy-method方式

​		在我们使用xml来进行bean元数据的配置时，我们可以使用init-menthod或者destroy-method属性来指定具体的方法作为我们初始化回调方法。