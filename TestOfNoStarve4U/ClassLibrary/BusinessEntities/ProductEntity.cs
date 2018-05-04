using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.DynamicData;
using System.Globalization;
using System.Threading.Tasks;

namespace BusinessEntities
{
    /// <summary>
    /// Identificator of Product
    /// </summary>
    public class ProductEntity
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public string Kind { get; set; }

        public override string ToString()
        {
            return Name;
        }
        
        public ProductEntity()
        {
        }

        public ProductEntity(int id, string name, string kind)
        {
            this.ID = id;
            this.Name = name;
            this.Kind = kind;
        }
        
    }
}
