using log4net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using BusinessEntities;
using BusinessServices;

namespace FridgeAPI.Controllers
{
    public class RecipeProductsController : ApiController
    {
        IRecipeProductServices recipeProductServices = new RecipeProductsServices();

        private static log4net.ILog Log { get; set; }

        ILog log = log4net.LogManager.GetLogger(typeof(RecipeProductsController));

        /// <summary>
        /// RecipeProducts.Get
        /// </summary>
        /// <param name="recipeID"></param>
        /// <returns></returns>
        [HttpGet]
        public HttpResponseMessage Get(int recipeID)
        {
            try
            {
                RecipeProductsEntity recipeProductsEntity = recipeProductServices.Get(recipeID);

                return Request.CreateResponse(HttpStatusCode.OK, recipeProductsEntity);
            }

            catch(Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// RecipeProducts.Add
        /// </summary>
        /// <param name="recipeProductsEntity"></param>
        /// <returns></returns>
        [HttpPost]
        public HttpResponseMessage Add(RecipeProductsEntity recipeProductsEntity)
        {
            try
            {
                recipeProductServices.Add(recipeProductsEntity);

                return Request.CreateResponse(HttpStatusCode.OK, recipeProductsEntity);
            }

            catch(Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
        }
    }
}