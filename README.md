````
_____________________________________________________________________________________________________
             /                /    __    __     /             ____   /                /             
            /                /____/_/___/ /__  /     ______ _/ / /__/                /              
           /                / ___/ / __  / _ \/| /| / / __ `/ / / _/                /               
          /                /__  / / /_/ /  __/ |/ |/ / /_/ / / ,< /                /                /
         /                /____/_/\__,_/\___/|__/|__/\__,_/_/_/|_|                /                /
________/________________/_________________/____________________/________________/________________/__
````

======

Sidewalk is a Java library built in collaboration with [JNodalXML](https://github.com/zachtaylor/JNodalXML). Where JNodal focuses on the XML document (parsing, modifying, creating, and writing), Sidewalk exists for interaction, specifically through XPath.

Sidewalk is a Maven project, as is JNodal. If you don't want to use Maven to manage the dependencies, you can check out the pom.xml to see where the required projects are located.

Some of the goals in the project include the following XPath items
- [x] Direct child node (/nodeName)
- [x] Ancestor node (//nodeName)
- [x] Direct child attribute (/@attrName)
- [x] Ancestor child attribute (//@attrName)
- [ ] Generic matching (/*)
- [ ] Current Node (.)
- [ ] Parent Node (..)
- [ ] Target filtering (/nodeName[1])
