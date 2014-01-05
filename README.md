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

Sidewalk is a Maven project, but JNodal can't currently be checked out via Maven. The approach I'm currently taking is to check out both, and Maven in Eclipse is able to resolve the dependency as a local one that way. I'm debating uploading the dependencies into this project, but haven't yet.

Some of the goals in the project include the following XPath items
- [x] Direct child node (/nodeName)
- [x] Ancestor node (//nodeName)
- [x] Direct child attribute (/@attrName)
- [x] Ancestor child attribute (//@attrName)
- [ ] Generic matching (/*)
- [ ] Current Node (.)
- [ ] Parent Node (..)
- [ ] Target filtering (/nodeName[1])
